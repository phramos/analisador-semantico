void teste3acwhileif() {
    int a;
    int b;
    a = 1;
    b = 10;

    while (a > b) {

        if (a < b) {
            b = b - 1;
        }

        a = a*2;
    }

    a = 0;
    b = 0;
}