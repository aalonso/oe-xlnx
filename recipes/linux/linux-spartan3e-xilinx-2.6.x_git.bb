# Copyright (C) 2009, Adrian Alonso <aalonso00@gmail.com>
# Released under the MIT license (see packages/COPYING)
SECTION = "kernel"
DESCRIPTION = "Linux kernel for Xilinx ML507 Virtex 5 fpga board"
LICENSE = "GPL"
DEPENDS = "git-native"
PR = "r1"
DEFAULT_PREFERENCE = "-1"
COMPATIBLE_MACHINE = "xilinx-spartan3e"
SRC_URI = "git://git.xilinx.com/linux-2.6-xlnx.git;protocol=git \
           file://microblaze_defconfig "

inherit kernel xilinx-bsp

S = "${WORKDIR}/git"


FILES_kernel-image = "/boot/zImage.elf"

export OS = "Linux"
ARCH = "microblaze"
KERNEL_IMAGETYPE = "zImage"
KERNEL_OUTPUT = "arch/microblaze/boot/images/zImage.elf"


do_configure() {
               
                install -m 644 ${WORKDIR}/microblaze_defconfig ${S}/.config
                make ARCH=${ARCH} oldconfig
}

do_stage_append () {
#need ppc platforms includes + friends in order for external kernel modules to compile as headers a$

       install -d ${STAGING_KERNEL_DIR}/arch/
       cp -pPR arch/microblaze ${STAGING_KERNEL_DIR}/arch/

       install -d ${STAGING_KERNEL_DIR}/include/asm
       cp -pPR include/asm-microblaze ${STAGING_KERNEL_DIR}/include/
}



do_deploy() {
        install -d ${DEPLOY_DIR_IMAGE}
        install -m 0644 arch/${ARCH}/boot/images/${KERNEL_IMAGETYPE}.elf \
                 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}
}

#seems like 2.6.21 kernel images have moved (or is this only for the Denx kernel ?)
#so we need to copy the kernel image where kernel.bbclass expects it to be
do_install_prepend() {
        install -m 0644 arch/${ARCH}/boot/images/${KERNEL_IMAGETYPE}.elf \
                        arch/${ARCH}/boot/${KERNEL_IMAGETYPE}
}
