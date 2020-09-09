# -*- coding: utf-8 -*-
# Author: Rowan
from project import db


class MetaTag(db.Model):
    """Meta tag model"""
    __tablename__ = 'mst_meta_tag'
    id = db.Column(db.Integer, unique=True, primary_key=True)
    v_name = db.Column(db.String)
    e_name = db.Column(db.String)
