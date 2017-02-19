# TODO take gdc branche from ${PV} variable
# TODO patch source if LAGUAGE contains "d" language
# TODO set SRCREV to TAG or other good commit

SRCREV = "${AUTOREV}"
SRC_URI += " git://github.com/D-Programming-GDC/GDC.git;branch=gdc-6;name=gdc-source;protocol=https"

SRC_URI[gdc-source.md5sum] = "cc60e2f9cef7d4085ea3c70a93c9534a"
SRC_URI[gdc-source.sha256sum] = "ce224eecd30c33e427244b04201ddee8f11b83a8b2c793317749289822d76906"

do_preconfigure_gdc () {
    cd ${WORKDIR}/git
    ./setup-gcc.sh ${S}
}

addtask do_preconfigure_gdc after do_unpack
do_preconfigure_gdc[depends] += "gnu-config-native:do_populate_sysroot autoconf-native:do_populate_sysroot"
do_preconfigure[depends] += "gcc-source-${PV}:do_preconfigure_gdc"
