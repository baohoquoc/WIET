# -*- coding: utf-8 -*-
# Author: Rowan

from project import db


class Survey(db.Model):
    """Survey model"""
    __tablename__ = 'tbl_survey'
    meta_tag_id = db.Column(db.Integer)
    tag_id = db.Column(db.Integer)
    image = db.Column(db.String, nullable=True)
    id = db.Column(db.Integer, primary_key=True, unique=True)