# -*- coding: utf-8 -*-
# Author: Rowan
import json
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestBookmark(unittest.TestCase):
    def testGetListBookmarks(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/bookmarks'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testCreateBookmark1(self):
        with app.test_client() as client:
            data = {
                "food_id": 2
            }
            res = client.post(DEFAULT_PATH.format('/bookmark'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testCreateBookmark2(self):
        with app.test_client() as client:
            data = {
                "food_id2": 2
            }
            res = client.post(DEFAULT_PATH.format('/bookmark'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)

    def testCreateBookmark3(self):
        with app.test_client() as client:
            data = {
                "food_id": 0
            }
            res = client.post(DEFAULT_PATH.format('/bookmark'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(402, res.status_code)

    def testRemoveBookmark(self):
        with app.test_client() as client:
            res = client.delete(DEFAULT_PATH.format('/bookmark/2'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
