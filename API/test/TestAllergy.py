# -*- coding: utf-8 -*-
# Author: Rowan
import json
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestAllergy(unittest.TestCase):
    def testAllergy1(self):
        with app.test_client() as client:
            data = {
                "tag_ids": [
                    4198,
                    4199
                ]
            }
            res = client.post(DEFAULT_PATH.format('/allergy'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testAllergy2(self):
        with app.test_client() as client:
            data = {
                "tag_ids2": [
                    4198,
                    4199
                ]
            }
            res = client.post(DEFAULT_PATH.format('/allergy'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)

    def testAllergy3(self):
        with app.test_client() as client:
            data = {
                "tag_ids": [
                    4,
                    4199
                ]
            }
            res = client.post(DEFAULT_PATH.format('/allergy'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(402, res.status_code)

    def testGetListAllergies(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/allergies'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testDeleteAllergy(self):
        with app.test_client() as client:
            res = client.delete(DEFAULT_PATH.format('/allergy/39'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
