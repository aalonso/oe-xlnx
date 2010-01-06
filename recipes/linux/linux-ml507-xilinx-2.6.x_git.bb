DESCRIPTION = "Linux kernel for Xilinx ML507 Virtex 5 fpga board"
# Copyright (C) 2009, Adrian Alonso <aalonso00@gmail.com>
# Released under the MIT license (see packages/COPYING)
SECTION = "kernel"
LICENSE = "GPL"
DEPENDS = "git-native"
PR = "r1"

SRC_URI = "git://git.xilinx.com/linux-2.6-xlnx.git;protocol=git \
           file://virtex5_defconfig "

S = "${WORKDIR}/git"

inherit kernel xilinx-bsp

do_configure() {
                install -m 644 ${WORKDIR}/virtex5_defconfig ${S}/.config
                make ARCH=${ARCH} oldconfig
}
#seems like 2.6.21 kernel images have moved (or is this only for the Denx kernel ?)
#so we need to copy the kernel image where kernel.bbclass expects it to be
do_install_prepend() {
        install -m 0644 arch/${ARCH}/boot/images/${KERNEL_IMAGETYPE}.elf \
                        arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
}
do_stage_append () {
       install -d ${STAGING_KERNEL_DIR}/arch/
       cp -pPR arch/ppc ${STAGING_KERNEL_DIR}/arch/
       cp -pPR arch/powerpc ${STAGING_KERNEL_DIR}/arch/
       install -d ${STAGING_KERNEL_DIR}/include/asm
       cp -pPR include/asm-ppc ${STAGING_KERNEL_DIR}/include/
       cp -pPR include/asm-powerpc ${STAGING_KERNEL_DIR}/include/
}

FILES_kernel-image = "/boot/zImage.elf"

export OS = "Linux"

ARCH = "powerpc"

COMPATIBLE_MACHINE = "xilinx-ml507"

DEFAULT_PREFERENCE = "-1"

KERNEL_IMAGETYPE = "zImage"
KERNEL_OUTPUT = "arch/powerpc/boot/images/zImage.elf"

do_deploy() {
        install -d ${DEPLOY_DIR_IMAGE}
        install -m 0644 arch/${ARCH}/boot/images/${KERNEL_IMAGETYPE}.elf \
                 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}
}
