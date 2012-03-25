#! /usr/bin/python2.6
import sys
import re
h = set()
l = list()
for line in sys.stdin.xreadlines():
    f = re.sub('[\W_]', ' ', line).lower().split()
    l.extend(f[:])
    for term in f:
        h.add(term)
print len(h), "/", len(l)

