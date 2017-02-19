require gcc-configure-prepend.inc

PROVIDES_append = " virtual/${TARGET_PREFIX}gdc"

do_compile_prepend () {
	export GDCFLAGS_FOR_TARGET="${TARGET_GDCFLAGS}"
}
