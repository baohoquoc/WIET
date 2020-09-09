# -*- coding: utf-8 -*-
# Author: Ned
import datetime

from project import db


class Rating(db.Model):
    """Rating model"""
    __tablename__ = 'rating'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    user_id = db.Column(db.String)
    food_id = db.Column(db.Integer)
    rate = db.Column(db.Integer)
    created_at = db.Column(db.DateTime, default=datetime.datetime.now)
    updated_at = db.Column(db.DateTime, default=datetime.datetime.now, onupdate=datetime.datetime.now)
