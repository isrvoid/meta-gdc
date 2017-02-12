SECTION = "devel"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSL-1.0;md5=65a7df9ad57aacf825fd252c4c33288c"

GCC_PV = "6.3.0"

S = "${TMPDIR}/work-shared/gcc-${GCC_PV}-${PR}/gcc-${GCC_PV}/libphobos"
B = "${WORKDIR}/${BP}"

do_configure () {
    cd ${B}
    relpath=${@os.path.relpath("${S}", "${B}")}
    # FIXME
    $relpath/configure --host=arm-poky-linux-gnueabi
}
