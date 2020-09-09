# -*- coding: utf-8 -*-
# Author: Ned
from project import db


class FoodTag(db.Model):
    """FoodTag model"""
    __tablename__ = 'food_tag'
    food_id = db.Column(db.Integer, primary_key=True)
    tag_id = db.Column(db.Integer, primary_key=True)
