# -*- coding: utf-8 -*-
# Author: Rowan, Ned
import datetime

from project import db


class Food(db.Model):
    """Food model"""
    __tablename__ = 'tbl_food'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    restaurant_id = db.Column(db.Integer)
    food_id = db.Column(db.Integer, unique=True)
    name = db.Column(db.String(100))
    price = db.Column(db.Integer)
    image = db.Column(db.String(200))
    average_score = db.Column(db.DECIMAL(asdecimal=False), nullable=True)
    total_vote = db.Column(db.Integer, nullable=True)
    total_view = db.Column(db.Integer, nullable=True)
    created_time = db.Column(db.DateTime, default=datetime.datetime.now)
    updated_time = db.Column(db.DateTime, default=datetime.datetime.now, onupdate=datetime.datetime.now)
