# -*- coding: utf-8 -*-
# Author: Rowan

from project import db


class City(db.Model):
    """City model"""
    __tablename__ = 'mst_city'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    name = db.Column(db.String)
    slug = db.Column(db.String)
    type = db.Column(db.String)
    name_with_type = db.Column(db.String)
    code = db.Column(db.Integer)
