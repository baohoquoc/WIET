# -*- coding: utf-8 -*-
# Author: Rowan
from flask import redirect, url_for, render_template, flash
from flask_login import login_user, logout_user

from project import admin, db, login, app
from project.models.Admin import Admin
from project.models.MetaTag import MetaTag
from project.models.Store import Store
from project.models.Survey import Survey
from project.models.Tag import Tag
from project.models.User import User
from project.utils.form_utils import LoginForm
from project.utils.view_utils import MyModelView, UserModelView, TagModelView, StoreModelView


@login.user_loader
def load_user(user_id):
    return Admin.query.get(user_id)


admin.add_view(UserModelView(User, db.session))
admin.add_view(MyModelView(Survey, db.session))
admin.add_view(TagModelView(Tag, db.session))
admin.add_view(TagModelView(MetaTag, db.session))
admin.add_view(StoreModelView(Store, db.session))


@app.route('/admin/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        username = form.username.data
        password = form.password.data
        admin_user = Admin.query.filter_by(name=username, pwd=password).first()
        if admin_user is not None:
            user = Admin.query.get(admin_user.id)
            login_user(user)
            flash('Logged in!')
            return redirect(url_for('admin.index'))
    return render_template('admin/login.html', form=form)


@app.route('/admin/logout')
def logout():
    logout_user()
    flash('Logged out!')
    return redirect(url_for('admin.index'))
