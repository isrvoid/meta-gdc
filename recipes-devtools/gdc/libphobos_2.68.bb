SECTION = "devel"

LICENSE = "Phobos"
LIC_FILES_CHKSUM = "file://phoboslicense.txt;beginline=4;endline=25;md5=802e9b3272bbda357a3653250ac97c0d"

GDCFLAGS_remove = "-fvisibility-inlines-hidden"

GCC_PV = "6.3.0"

S = "${TMPDIR}/work-shared/gcc-${GCC_PV}-${PR}/gcc-${GCC_PV}/libphobos"
B = "${WORKDIR}/${BP}"

do_configure () {
    cd ${B}
    relpath=${@os.path.relpath("${S}", "${B}")}
    # FIXME
    $relpath/configure --host=arm-poky-linux-gnueabi
}

do_compile () {
    cd ${B}
    oe_runmake MULTIBUILDTOP=${B}
}
