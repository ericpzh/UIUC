#ifndef FOO_H
#define FOO_H

#include <string>

using namespace std;

namespace potd {
    class foo {
        public:
            foo(string);
            foo(const foo &);
            foo & operator=(const foo &);
            ~foo();
            static int get_count();
            string get_name();
        private:
            static int count_;
            string name_;
    };
}

#endif
