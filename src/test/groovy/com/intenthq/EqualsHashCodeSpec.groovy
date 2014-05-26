package com.intenthq

import spock.lang.Specification
import spock.lang.Unroll

abstract class EqualsHashCodeSpec extends Specification {

    private static FAKE_PROPERTY = 'dd2060d0-a28d-11e0-8264-0800200c9a66'

    protected abstract createObjectToCompare()

    protected abstract modifiedPropertiesIncludedInEqualsAndHashCode()

    void 'equals to null returns false'() {
        setup:
        def object = createObjectToCompare()

        expect:
        !object.equals(null)
    }

    void 'equals is reflexive'() {
        setup:
        def object = createObjectToCompare()

        expect:
        object.equals(object)
    }

    void 'equals is transitive'() {
        setup:
        def object = createObjectToCompare()
        def object2 = createObjectToCompare()
        def object3 = createObjectToCompare()

        expect:
        object.equals(object2)
        object2.equals(object3)
        object.equals(object3)
    }

    void 'equals is symmetric'() {
        setup:
        def object = createObjectToCompare()
        def object2 = createObjectToCompare()

        expect:
        object.equals(object2)
        object2.equals(object)
    }

    void 'hashCode of two equal objects is equal'() {
        setup:
        def object = createObjectToCompare()
        def object2 = createObjectToCompare()

        expect:
        object.hashCode() == object2.hashCode()
    }

    void 'equals is consistent'() {
        setup:
        def object = createObjectToCompare()
        def object2 = createObjectToCompare()

        expect:
        object.equals(object2)
        object.equals(object2)
    }

    void 'hashCode is consistent'() {
        setup:
        def object = createObjectToCompare()

        expect:
        object.equals(object)
        object.hashCode() == object.hashCode()
    }

    void 'equals to incompatible type returns false'() {
        setup:
        def object = createObjectToCompare()

        expect:
        !object.equals(new Object())
    }

    @Unroll("equals and hashCode use #property")
    void 'equals and hashCode use some properties'() {
        setup:
        def object = createObjectToCompare()
        def object2 = createModifiedObject(property)

        expect:
        !object.equals(object2)

        where:
        property << modifiedPropertiesIncludedInEqualsAndHashCode()
    }

    @Unroll("equals and hashCode ignore #property")
    void 'equals and hashCode ignore some properties'() {
        setup:
        def object = createObjectToCompare()
        def object2 = property.key != FAKE_PROPERTY ? createModifiedObject(property) : createObjectToCompare()

        expect:
        object.equals(object2)
        object.hashCode() == object2.hashCode()

        where:
        property << modifiedPropertiesIgnoredInEqualsAndHashCode()
    }

    def createModifiedObject(modifiedProperty) {
        def object = createObjectToCompare()
        object[modifiedProperty.key] = evaluateValue(modifiedProperty.value)
        object
    }

    private evaluateValue(value) {
        value instanceof Closure ? value() : value
    }

    def modifiedPropertiesIgnoredInEqualsAndHashCode() {
        ["${FAKE_PROPERTY}": null]
    }
}
