require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-configure-common.inc

SECTION = "devel"

LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSL-1.0;md5=65a7df9ad57aacf825fd252c4c33288c"

BINV = "6.3.0"

EXTRA_OECONF_PATHS = "\
    --with-sysroot=/not/exist \
    --with-build-sysroot=${STAGING_DIR_TARGET} \
"
GDCFLAGS_remove = "-fvisibility-inlines-hidden"

do_configure () {
	rm -rf ${B}/${TARGET_SYS}/libphobos/
	mkdir -p ${B}/${TARGET_SYS}/libphobos/
	cd ${B}/${TARGET_SYS}/libphobos/
	chmod a+x ${S}/libphobos/configure
	relpath=${@os.path.relpath("${S}/libphobos", "${B}/${TARGET_SYS}/libphobos")}
	$relpath/configure ${CONFIGUREOPTS} ${EXTRA_OECONF}
}
EXTRACONFFUNCS += "extract_stashed_builddir"
do_configure[depends] += "${COMPILERDEP}"

do_compile () {
	cd ${B}/${TARGET_SYS}/libphobos/
	oe_runmake MULTIBUILDTOP=${B}/${TARGET_SYS}/libphobos/
}

do_install () {
	cd ${B}/${TARGET_SYS}/libphobos/
	oe_runmake 'DESTDIR=${D}' MULTIBUILDTOP=${B}/${TARGET_SYS}/libphobos/ install
	if [ -d ${D}${libdir}/gcc/${TARGET_SYS}/${BINV}/finclude ]; then
		rmdir --ignore-fail-on-non-empty -p ${D}${libdir}/gcc/${TARGET_SYS}/${BINV}/finclude
	fi
	if [ -d ${D}${infodir} ]; then
		rmdir --ignore-fail-on-non-empty -p ${D}${infodir}
	fi
	chown -R root:root ${D}
}

# FIXME
#INHIBIT_DEFAULT_DEPS = "1"
#DEPENDS = "gcc-runtime"

BBCLASSEXTEND = "nativesdk"

PACKAGES = "\
    ${PN}-dbg \
    libphobos \
    libphobos-dev \
    libphobos-staticdev \
"
FILES_${PN} = "\
    ${libdir}/libgphobos.so.* \
    ${libdir}/libgdruntime.so.* \
"
FILES_${PN}-dev = "\
    ${libdir}/gcc/${TARGET_SYS}/${BINV}/include/d \
    ${libdir}/libgphobos*.so \
    ${libdir}/libgphobos.spec \
    ${libdir}/libgphobos.la \
    ${libdir}/libgdruntime*.so \
    ${libdir}/libgdruntime.spec \
    ${libdir}/libgdruntime.la \
    ${libdir}/gcc/${TARGET_SYS}/${BINV}/libgphobosbegin.* \
    ${libdir}/gcc/${TARGET_SYS}/${BINV}/libgdruntimebegin.* \
    ${libdir}/gcc/${TARGET_SYS}/${BINV}/libcaf_single* \
"
FILES_${PN}-staticdev = "\
    ${libdir}/libgphobos.a \
    ${libdir}/libgdruntime.a \
"

do_package_write_ipk[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
do_package_write_deb[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
do_package_write_rpm[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
