require autoconf.inc

PR = "${INC_PR}.0"

DEFAULT_PREFERENCE = "-1"

PARALLEL_MAKE = ""

SRC_URI += "file://autoreconf-exclude.patch;patch=1 \
		file://autoreconf-foreign.patch;patch=1 \
	    file://autoheader-nonfatal-warnings.patch;patch=1 \
		file://autoreconf-gnuconfigize.patch;patch=1 \
		file://config-site.patch;patch=1"

