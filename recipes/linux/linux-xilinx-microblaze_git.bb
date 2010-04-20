# Copyright (C) 2009, Adrian Alonso <aalonso00@gmail.com>
# Released under the MIT license (see packages/COPYING)
SECTION = "kernel"
DESCRIPTION = "Linux kernel for Xilinx SpartanE fpga board"
LICENSE = "GPL"
require linux.inc
# PR = "r1"
DEFAULT_PREFERENCE = "-1"
COMPATIBLE_MACHINE = "xilinx-spartan3e"
# Experimental
TAG = "xmb_beta_v3"

SRC_URI = "git://git.xilinx.com/linux-2.6-xlnx.git;protocol=git;tag=${TAG} \
           file://microblaze_defconfig "

#inherit kernel xilinx-bsp

S = "${WORKDIR}/git"


#FILES_kernel-image = "/boot/zImage.elf"

#export OS = "Linux"
ARCH = "microblaze"
KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE_microblaze = "arch/${ARCH}/boot/dts/system.dts"
#KERNEL_OUTPUT = "arch/microblaze/boot/images/zImage.elf"

