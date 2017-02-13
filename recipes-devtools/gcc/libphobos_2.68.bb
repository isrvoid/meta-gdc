SECTION = "devel"

# FIXME use correct license
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSL-1.0;md5=65a7df9ad57aacf825fd252c4c33288c"

GCC_PV = "6.3.0"

S = "${TMPDIR}/work-shared/gcc-${GCC_PV}-${PR}/gcc-${GCC_PV}/libphobos"
B = "${WORKDIR}/${BP}"

# FIXME
GDC = "${CXX}"
GDC_remove = "arm-poky-linux-gnueabi-g++"
GDC_prepend = "arm-poky-linux-gnueabi-gdc "
GDCFLAGS = "${CFLAGS}"
BUILD_GDC="gdc"

do_configure () {
    export GDC="${GDC}"
    export BUILD_GDC="${BUILD_GDC}"
    export GDCFLAGS="${GDCFLAGS}"
    cd ${B}
    relpath=${@os.path.relpath("${S}", "${B}")}
    # FIXME
    $relpath/configure --host=arm-poky-linux-gnueabi
}

do_compile () {
    export GDC="${GDC}"
    export GDCFLAGS="${GDCFLAGS}"
    export BUILD_GDC="${BUILD_GDC}"
    cd ${B}
    oe_runmake MULTIBUILDTOP=${B}
}
