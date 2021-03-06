/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.middle.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-05-11")
public class SysUserResponse implements org.apache.thrift.TBase<SysUserResponse, SysUserResponse._Fields>, java.io.Serializable, Cloneable, Comparable<SysUserResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SysUserResponse");

  private static final org.apache.thrift.protocol.TField SYS_USERS_FIELD_DESC = new org.apache.thrift.protocol.TField("sysUsers", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField STATUS_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("statusCode", org.apache.thrift.protocol.TType.I32, (short)255);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SysUserResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SysUserResponseTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<SysUser> sysUsers; // required
  /**
   * 
   * @see hust.software.elon.safety.common.domain.StatusCode
   */
  public @org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.StatusCode statusCode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SYS_USERS((short)1, "sysUsers"),
    /**
     * 
     * @see hust.software.elon.safety.common.domain.StatusCode
     */
    STATUS_CODE((short)255, "statusCode");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SYS_USERS
          return SYS_USERS;
        case 255: // STATUS_CODE
          return STATUS_CODE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.STATUS_CODE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SYS_USERS, new org.apache.thrift.meta_data.FieldMetaData("sysUsers", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, SysUser.class))));
    tmpMap.put(_Fields.STATUS_CODE, new org.apache.thrift.meta_data.FieldMetaData("statusCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, hust.software.elon.safety.common.domain.StatusCode.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SysUserResponse.class, metaDataMap);
  }

  public SysUserResponse() {
    this.statusCode = hust.software.elon.safety.common.domain.StatusCode.SUCCESS;

  }

  public SysUserResponse(
    java.util.List<SysUser> sysUsers)
  {
    this();
    this.sysUsers = sysUsers;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SysUserResponse(SysUserResponse other) {
    if (other.isSetSysUsers()) {
      java.util.List<SysUser> __this__sysUsers = new java.util.ArrayList<SysUser>(other.sysUsers.size());
      for (SysUser other_element : other.sysUsers) {
        __this__sysUsers.add(new SysUser(other_element));
      }
      this.sysUsers = __this__sysUsers;
    }
    if (other.isSetStatusCode()) {
      this.statusCode = other.statusCode;
    }
  }

  public SysUserResponse deepCopy() {
    return new SysUserResponse(this);
  }

  @Override
  public void clear() {
    this.sysUsers = null;
    this.statusCode = hust.software.elon.safety.common.domain.StatusCode.SUCCESS;

  }

  public int getSysUsersSize() {
    return (this.sysUsers == null) ? 0 : this.sysUsers.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<SysUser> getSysUsersIterator() {
    return (this.sysUsers == null) ? null : this.sysUsers.iterator();
  }

  public void addToSysUsers(SysUser elem) {
    if (this.sysUsers == null) {
      this.sysUsers = new java.util.ArrayList<SysUser>();
    }
    this.sysUsers.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<SysUser> getSysUsers() {
    return this.sysUsers;
  }

  public SysUserResponse setSysUsers(@org.apache.thrift.annotation.Nullable java.util.List<SysUser> sysUsers) {
    this.sysUsers = sysUsers;
    return this;
  }

  public void unsetSysUsers() {
    this.sysUsers = null;
  }

  /** Returns true if field sysUsers is set (has been assigned a value) and false otherwise */
  public boolean isSetSysUsers() {
    return this.sysUsers != null;
  }

  public void setSysUsersIsSet(boolean value) {
    if (!value) {
      this.sysUsers = null;
    }
  }

  /**
   * 
   * @see hust.software.elon.safety.common.domain.StatusCode
   */
  @org.apache.thrift.annotation.Nullable
  public hust.software.elon.safety.common.domain.StatusCode getStatusCode() {
    return this.statusCode;
  }

  /**
   * 
   * @see hust.software.elon.safety.common.domain.StatusCode
   */
  public SysUserResponse setStatusCode(@org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.StatusCode statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public void unsetStatusCode() {
    this.statusCode = null;
  }

  /** Returns true if field statusCode is set (has been assigned a value) and false otherwise */
  public boolean isSetStatusCode() {
    return this.statusCode != null;
  }

  public void setStatusCodeIsSet(boolean value) {
    if (!value) {
      this.statusCode = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case SYS_USERS:
      if (value == null) {
        unsetSysUsers();
      } else {
        setSysUsers((java.util.List<SysUser>)value);
      }
      break;

    case STATUS_CODE:
      if (value == null) {
        unsetStatusCode();
      } else {
        setStatusCode((hust.software.elon.safety.common.domain.StatusCode)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SYS_USERS:
      return getSysUsers();

    case STATUS_CODE:
      return getStatusCode();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SYS_USERS:
      return isSetSysUsers();
    case STATUS_CODE:
      return isSetStatusCode();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof SysUserResponse)
      return this.equals((SysUserResponse)that);
    return false;
  }

  public boolean equals(SysUserResponse that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_sysUsers = true && this.isSetSysUsers();
    boolean that_present_sysUsers = true && that.isSetSysUsers();
    if (this_present_sysUsers || that_present_sysUsers) {
      if (!(this_present_sysUsers && that_present_sysUsers))
        return false;
      if (!this.sysUsers.equals(that.sysUsers))
        return false;
    }

    boolean this_present_statusCode = true && this.isSetStatusCode();
    boolean that_present_statusCode = true && that.isSetStatusCode();
    if (this_present_statusCode || that_present_statusCode) {
      if (!(this_present_statusCode && that_present_statusCode))
        return false;
      if (!this.statusCode.equals(that.statusCode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetSysUsers()) ? 131071 : 524287);
    if (isSetSysUsers())
      hashCode = hashCode * 8191 + sysUsers.hashCode();

    hashCode = hashCode * 8191 + ((isSetStatusCode()) ? 131071 : 524287);
    if (isSetStatusCode())
      hashCode = hashCode * 8191 + statusCode.getValue();

    return hashCode;
  }

  @Override
  public int compareTo(SysUserResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetSysUsers(), other.isSetSysUsers());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSysUsers()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sysUsers, other.sysUsers);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetStatusCode(), other.isSetStatusCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatusCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.statusCode, other.statusCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SysUserResponse(");
    boolean first = true;

    sb.append("sysUsers:");
    if (this.sysUsers == null) {
      sb.append("null");
    } else {
      sb.append(this.sysUsers);
    }
    first = false;
    if (isSetStatusCode()) {
      if (!first) sb.append(", ");
      sb.append("statusCode:");
      if (this.statusCode == null) {
        sb.append("null");
      } else {
        sb.append(this.statusCode);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SysUserResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SysUserResponseStandardScheme getScheme() {
      return new SysUserResponseStandardScheme();
    }
  }

  private static class SysUserResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<SysUserResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SysUserResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SYS_USERS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.sysUsers = new java.util.ArrayList<SysUser>(_list8.size);
                @org.apache.thrift.annotation.Nullable SysUser _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new SysUser();
                  _elem9.read(iprot);
                  struct.sysUsers.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setSysUsersIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 255: // STATUS_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.statusCode = hust.software.elon.safety.common.domain.StatusCode.findByValue(iprot.readI32());
              struct.setStatusCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SysUserResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.sysUsers != null) {
        oprot.writeFieldBegin(SYS_USERS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.sysUsers.size()));
          for (SysUser _iter11 : struct.sysUsers)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.statusCode != null) {
        if (struct.isSetStatusCode()) {
          oprot.writeFieldBegin(STATUS_CODE_FIELD_DESC);
          oprot.writeI32(struct.statusCode.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SysUserResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SysUserResponseTupleScheme getScheme() {
      return new SysUserResponseTupleScheme();
    }
  }

  private static class SysUserResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<SysUserResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SysUserResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSysUsers()) {
        optionals.set(0);
      }
      if (struct.isSetStatusCode()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSysUsers()) {
        {
          oprot.writeI32(struct.sysUsers.size());
          for (SysUser _iter12 : struct.sysUsers)
          {
            _iter12.write(oprot);
          }
        }
      }
      if (struct.isSetStatusCode()) {
        oprot.writeI32(struct.statusCode.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SysUserResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list13 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRUCT);
          struct.sysUsers = new java.util.ArrayList<SysUser>(_list13.size);
          @org.apache.thrift.annotation.Nullable SysUser _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new SysUser();
            _elem14.read(iprot);
            struct.sysUsers.add(_elem14);
          }
        }
        struct.setSysUsersIsSet(true);
      }
      if (incoming.get(1)) {
        struct.statusCode = hust.software.elon.safety.common.domain.StatusCode.findByValue(iprot.readI32());
        struct.setStatusCodeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

