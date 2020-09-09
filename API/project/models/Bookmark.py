# -*- coding: utf-8 -*-
# Author: Rowan

from project import db


class Bookmark(db.Model):
    """Bookmark model"""
    __tablename__ = 'tbl_bookmark'
    id = db.Column(db.Integer, primary_key=True, unique=True)
    uid = db.Column(db.String)
    food_id = db.Column(db.Integer)
