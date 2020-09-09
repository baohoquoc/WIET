# -*- coding: utf-8 -*-
# Author: Ned


def is_numeric(value):
    try:
        float(value)
        return True
    except ValueError:
        return False
