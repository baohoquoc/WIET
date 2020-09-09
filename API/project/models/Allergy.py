# -*- coding: utf-8 -*-
# Author: Rowan

from project import db


class Allergy(db.Model):
    """Allergy model"""
    __tablename__ = 'tbl_allergy'
    id = db.Column(db.Integer, primary_key=True, unique=True)
    uid = db.Column(db.String)
    meta_tag_id = db.Column(db.Integer)
