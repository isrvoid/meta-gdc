SECTION = "devel"

LICENSE = "Phobos"
LIC_FILES_CHKSUM = "file://phoboslicense.txt;beginline=4;endline=25;md5=802e9b3272bbda357a3653250ac97c0d"

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
