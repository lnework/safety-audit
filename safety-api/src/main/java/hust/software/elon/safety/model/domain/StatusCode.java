/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.model.domain;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-16")
public enum StatusCode implements org.apache.thrift.TEnum {
  SUCCESS(0),
  ERROR(1);

  private final int value;

  private StatusCode(int value) {
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
  public static StatusCode findByValue(int value) { 
    switch (value) {
      case 0:
        return SUCCESS;
      case 1:
        return ERROR;
      default:
        return null;
    }
  }
}