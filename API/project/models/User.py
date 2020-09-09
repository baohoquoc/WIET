# -*- coding: utf-8 -*-
# Author: Rowan
import datetime

from project import db


class User(db.Model):
    """User model"""
    __tablename__ = 'tbl_user'
    uid = db.Column(db.String, unique=True, primary_key=True)
    email = db.Column(db.String, unique=True)
    dob = db.Column(db.Date, default=datetime.datetime(year=1900, month=1, day=1))
    fullname = db.Column(db.String(50))
    is_vegetarian = db.Column(db.Boolean, default=False)
    avatar = db.Column(db.String, nullable=True)
    created_at = db.Column(db.DateTime, default=datetime.datetime.now)
    updated_at = db.Column(db.DateTime, default=datetime.datetime.now, onupdate=datetime.datetime.now)
    is_first_login = db.Column(db.Boolean, default=True)
    location = db.Column(db.String, nullable=True, default='Đà Nẵng')
