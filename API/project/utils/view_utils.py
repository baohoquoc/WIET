# -*- coding: utf-8 -*-
# Author: Rowan
from flask import redirect, url_for
from flask_admin import AdminIndexView, expose
from flask_admin.contrib.sqla import ModelView
from flask_login import current_user


class MyHomeView(AdminIndexView):
    @expose('/')
    def index(self):
        return self.render('admin/index.html', is_auth=current_user.is_authenticated)


class MyModelView(ModelView):
    create_modal = True
    edit_modal = True

    def is_accessible(self):
        return current_user.is_authenticated

    def inaccessible_callback(self, name, **kwargs):
        return redirect(url_for('login'))


class UserModelView(MyModelView):
    can_create = False
    column_exclude_list = ['avatar', 'created_at', 'updated_at', 'is_first_login']
    form_widget_args = {
        'email': {
            'disabled': True
        },
        'avatar': {
            'disabled': True
        },
        'created_at': {
            'disabled': True
        },
        'updated_at': {
            'disabled': True
        },
        'location': {
            'disabled': True
        }
    }


class TagModelView(MyModelView):
    can_create = False
    can_delete = False


class StoreModelView(MyModelView):
    pass
