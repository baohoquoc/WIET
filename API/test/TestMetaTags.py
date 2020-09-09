# -*- coding: utf-8 -*-
# Author: Rowan
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestMetaTags(unittest.TestCase):
    def testGetMetaTags1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/meta_tags'), headers=AUTH_HEADERS)
            self.assertEqual(200, res.status_code)

    def testGetMetaTags2(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/meta_tags?search=Nướng'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetMetaTagsByTagId1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/meta_tags/4221'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetMetaTagsByTagId2(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/meta_tags/42'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
