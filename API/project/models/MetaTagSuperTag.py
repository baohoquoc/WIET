# -*- coding: utf-8 -*-
# Author: Ned
from project import db


class MetaTagSuperTag(db.Model):
    """MetaTagSuperTag model"""
    __tablename__ = 'meta_tag_super_tag'
    meta_tag_id = db.Column(db.Integer, primary_key=True)
    super_tag_id = db.Column(db.Integer, primary_key=True)