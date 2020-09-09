# -*- coding: utf-8 -*-
# Author: Rowan
import datetime

from project import db


class FCMToken(db.Model):
    """FCM token model"""
    __tablename__ = 'tbl_fcm_token'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    uid = db.Column(db.String)
    fcm_token = db.Column(db.String)
    created_at = db.Column(db.DateTime, default=datetime.datetime.now)
    updated_at = db.Column(db.DateTime, default=datetime.datetime.now, onupdate=datetime.datetime.now)
