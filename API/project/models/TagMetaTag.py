# -*- coding: utf-8 -*-
# Author: Rowan
from project import db


class TagMetaTag(db.Model):
    """TagMetaTag model"""
    __tablename__ = 'tag_meta_tag'
    tag_id = db.Column(db.Integer, primary_key=True)
    meta_tag_id = db.Column(db.Integer, primary_key=True)
