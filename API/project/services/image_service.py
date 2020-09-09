# -*- coding: utf-8 -*-
# Author: Rowan
from random import randrange

from project.dao.recommend_dao import is_vegan_user

DEFAULT_LIST_IMAGE_URL = ['https://2.pik.vn/2020d880f035-5f0c-40bf-99ae-fdddf40266b9.jpg',
                          'https://2.pik.vn/202097db5542-9edf-4358-ae3c-bc43a7156b77.jpg',
                          'https://2.pik.vn/2020b153ec69-b123-49fe-8408-cae5f2a878f1.jpg',
                          'https://2.pik.vn/20204256eff8-f38c-434e-a9f5-d5ad6e2a3242.jpg',
                          'https://2.pik.vn/2020e132840b-20ad-4b88-8515-6ec724e3d5bf.jpg',
                          'https://2.pik.vn/20206defbac3-9a83-4885-9855-9cdf6333ae43.jpg',
                          'https://2.pik.vn/20204b029b29-1d25-4156-8a30-356a2152d97a.jpg',
                          'https://2.pik.vn/202010a51ad9-54ff-4ec7-95ab-12a4dc5eb0a3.jpg',
                          'https://2.pik.vn/202082fce768-1920-457e-9ef0-e100f4354f4a.jpg',
                          'https://2.pik.vn/2020c6ccf5b1-6d3d-42d5-b72d-93a9dc7f533c.jpg']
VEGETARIAN_IMAGE_URL = ['https://2.pik.vn/2020f747a740-3ee8-446b-96b3-575a9ceaa25e.jpg',
                        'https://2.pik.vn/2020324d0254-aec3-4343-86d8-ad3eb64c7bca.jpg',
                        'https://2.pik.vn/202082b81072-c244-48fd-9e5a-f96fe443de43.jpg',
                        'https://2.pik.vn/202068ac52f6-926e-4611-acd2-c86b02f03a85.jpg',
                        'https://2.pik.vn/202065f038e0-78c5-4efc-a155-0779a1c51f18.jpg',
                        'https://2.pik.vn/202046c3b596-a804-441e-a80d-e2c49ad97d1c.jpg',
                        'https://2.pik.vn/2020e6a4c5b3-c700-4114-8656-236a308aa143.jpg',
                        'https://2.pik.vn/2020ab48273f-f139-4b22-ac4a-53fec6a697d8.jpg',
                        'https://2.pik.vn/202072c27e24-4b61-4c45-bb6a-96df23d479e8.jpg',
                        'https://2.pik.vn/2020b58420f6-dcef-41ea-94ad-d04b970a51ab.jpg']
NO_IMAGE_URL = '/style/images/deli-dish-no-image.png'

PROTOCOL = 'https:'


def update_protocol_for_images(image_url, uid):
    """Update protocol for images

    :param uid: uid
    :param image_url: image url
    :return: image with full protocol
    """
    if image_url == NO_IMAGE_URL:
        if is_vegan_user(uid):
            return VEGETARIAN_IMAGE_URL[randrange(len(VEGETARIAN_IMAGE_URL))]
        return DEFAULT_LIST_IMAGE_URL[randrange(len(DEFAULT_LIST_IMAGE_URL))]
    if image_url[0:6] != PROTOCOL:
        return PROTOCOL + image_url
    return image_url


def update_protocol_for_a_list_of_dict(key, list_dict, uid):
    """Update protocol for a list of dict

    :param uid: uid
    :param key: key
    :param list_dict: list dict
    :return: list dict updated
    """
    if list_dict is not None:
        for item in list_dict:
            item[key] = update_protocol_for_images(item[key], uid)
        return list_dict
    return None


def update_protocol_for_a_dict(key, dict_item, uid):
    """Update protocol for a dict

    :param uid: uid
    :param key: key
    :param dict_item: dict item
    :return: updated dict item
    """
    if dict_item is not None:
        dict_item[key] = update_protocol_for_images(dict_item[key], uid)
        return dict_item
    else:
        return None
