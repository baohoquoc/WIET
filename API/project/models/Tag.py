# -*- coding: utf-8 -*-
# Author: Rowan
from project import db


class Tag(db.Model):
    """Tag model"""
    __tablename__ = 'mst_tag'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    name = db.Column(db.String)
