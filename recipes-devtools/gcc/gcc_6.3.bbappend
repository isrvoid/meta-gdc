LICENSE = "GPL"


do_configure[depends] += "gcc-source-${PV}:do_add_gdc"
