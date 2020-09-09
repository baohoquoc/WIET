# -*- coding: utf-8 -*-
# Author: Ned
from project import db


class SuperTag(db.Model):
    """Super tag model"""
    __tablename__ = 'mst_super_tag'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    name = db.Column(db.String)