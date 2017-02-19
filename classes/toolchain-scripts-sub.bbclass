inherit toolchain-scripts

toolchain_shared_env_script_prepend () {
	echo 'export GDC="${TARGET_PREFIX}gdc ${TARGET_CC_ARCH} -shared-libphobos -I$SDKTARGETSYSROOT/usr/lib/gcc/${TARGET_SYS}/6.3.0/include/d --sysroot=$SDKTARGETSYSROOT"' >> $script
	echo 'export GDCFLAGS="${TARGET_GDCFLAGS}"' >> $script
}
