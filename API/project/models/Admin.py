# -*- coding: utf-8 -*-
# Author: Rowan

from flask_login import UserMixin

from project import db


class Admin(db.Model, UserMixin):
    """Allergy model"""
    __tablename__ = 'admin'
    name = db.Column(db.String)
    pwd = db.Column(db.String)
    id = db.Column(db.Integer, primary_key=True)
