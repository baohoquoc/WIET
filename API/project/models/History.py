# -*- coding: utf-8 -*-
# Author: Rowan
import datetime

from project import db


class History(db.Model):
    """History model"""
    __tablename__ = 'tbl_history'
    id = db.Column(db.Integer, primary_key=True, unique=True)
    uid = db.Column(db.String)
    food_id = db.Column(db.Integer)
    created_at = db.Column(db.DateTime, default=datetime.datetime.now)
