# -*- coding: utf-8 -*-
# Author: Rowan

from project import db


class Store(db.Model):
    """Store model"""
    __tablename__ = 'tbl_store'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    restaurant_id = db.Column(db.Integer)
    food_name = db.Column(db.String(500))
    address = db.Column(db.String(500))
    category = db.Column(db.String(100))
    id_food_category = db.Column(db.Integer, nullable=True)
    latitude = db.Column(db.DECIMAL(asdecimal=False))
    longitude = db.Column(db.DECIMAL(asdecimal=False))
    worktime = db.Column(db.String(20), nullable=True)
    rate = db.Column(db.DECIMAL(asdecimal=False), nullable=True)
    menu = db.Column(db.String(500), default='Menu trống')
    image_path = db.Column(db.String(500), nullable=True)
    totalreview = db.Column(db.Integer, default=0)
    view = db.Column(db.Integer, default=0)
