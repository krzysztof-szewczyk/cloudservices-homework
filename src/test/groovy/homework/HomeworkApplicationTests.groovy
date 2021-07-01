package homework

import spock.lang.Specification

class HomeworkApplicationTests extends Specification{

    def test(){
        when:
        def a = 1
        then:
        a == a
    }
}
