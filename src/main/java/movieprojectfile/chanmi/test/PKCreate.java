package movieprojectfile.chanmi.test;

import org.apache.commons.lang3.RandomStringUtils;

public class PKCreate {
        public static void main(String[] args) {

            int count = 100;

            for (int i = 0; i < 30; i++) {
                String ExPKCreate = RandomStringUtils.randomAlphanumeric(count);
                System.out.println(i + "번 :  " + ExPKCreate);
                System.out.println();
            }
        }

// 참고
// https://jookipedia.tistory.com/22
// https://computer-science-student.tistory.com/526
}
