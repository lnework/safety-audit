/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.common.domain;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-23")
public enum ObjectType implements org.apache.thrift.TEnum {
  NONE(0),
  AUDIO(1);

  private final int value;

  private ObjectType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static ObjectType findByValue(int value) { 
    switch (value) {
      case 0:
        return NONE;
      case 1:
        return AUDIO;
      default:
        return null;
    }
  }
}
