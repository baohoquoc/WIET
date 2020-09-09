# -*- coding: utf-8 -*-
import glob
import os

# load all modules
__all__ = [os.path.basename(f)[:-3] for f in glob.glob(os.path.dirname(__file__) + "/*.py")]
