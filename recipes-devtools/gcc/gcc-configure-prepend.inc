do_configure_prepend() {
	if [ "${BUILD_SYS}" != "${HOST_SYS}" ]; then
		export GDC_FOR_TARGET="${GDC}"
	fi
	export GDC_FOR_BUILD="${BUILD_GDC}"
	export GDCFLAGS_FOR_BUILD="${BUILD_GDCFLAGS}"
	export GDCFLAGS_FOR_TARGET="${TARGET_GDCFLAGS}"
}
