# -*- coding: utf-8 -*-
# Author: Rowan

from project import db


class ALSRecommend(db.Model):
    """ALSRecommend model"""
    __tablename__ = 'tbl_als_recommend'
    id = db.Column(db.Integer, primary_key=True, unique=True)
    user_id = db.Column(db.String)
    food_id = db.Column(db.Integer)
    rating = db.Column(db.DECIMAL(asdecimal=False))
