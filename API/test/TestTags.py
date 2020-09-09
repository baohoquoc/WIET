# -*- coding: utf-8 -*-
# Author: Rowan
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestTags(unittest.TestCase):
    def testGetTags1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/tags'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetTagsByMetaTagId1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/tags/1'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetTagsByMetaTagId2(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/tags/0'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
