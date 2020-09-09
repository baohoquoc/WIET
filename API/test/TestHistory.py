# -*- coding: utf-8 -*-
# Author: Rowan
import json
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestHistory(unittest.TestCase):
    def testCreateHistory1(self):
        with app.test_client() as client:
            data = {
                "food_id": 2
            }
            res = client.post(DEFAULT_PATH.format('/history'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testCreateHistory2(self):
        with app.test_client() as client:
            data = {
                "food_id2": 2
            }
            res = client.post(DEFAULT_PATH.format('/history'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)

    def testCreateHistory3(self):
        with app.test_client() as client:
            data = {
                "food_id": 0
            }
            res = client.post(DEFAULT_PATH.format('/history'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(402, res.status_code)

    def testGetListHistories(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/histories'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testDeleteHistory(self):
        with app.test_client() as client:
            res = client.delete(DEFAULT_PATH.format('/history/10'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
