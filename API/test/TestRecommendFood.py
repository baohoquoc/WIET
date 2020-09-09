# -*- coding: utf-8 -*-
# Author: Rowan
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestRecommendFood(unittest.TestCase):
    def testGetMetaTags1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/recommend?location=Đà Nẵng'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetMetaTags2(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/recommend?location=1'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)