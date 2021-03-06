/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.middle.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-05-11")
public class SysUserRequest implements org.apache.thrift.TBase<SysUserRequest, SysUserRequest._Fields>, java.io.Serializable, Cloneable, Comparable<SysUserRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SysUserRequest");

  private static final org.apache.thrift.protocol.TField USER_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("userIds", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SysUserRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SysUserRequestTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.Long> userIds; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_IDS((short)1, "userIds");

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
        case 1: // USER_IDS
          return USER_IDS;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_IDS, new org.apache.thrift.meta_data.FieldMetaData("userIds", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SysUserRequest.class, metaDataMap);
  }

  public SysUserRequest() {
  }

  public SysUserRequest(
    java.util.List<java.lang.Long> userIds)
  {
    this();
    this.userIds = userIds;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SysUserRequest(SysUserRequest other) {
    if (other.isSetUserIds()) {
      java.util.List<java.lang.Long> __this__userIds = new java.util.ArrayList<java.lang.Long>(other.userIds);
      this.userIds = __this__userIds;
    }
  }

  public SysUserRequest deepCopy() {
    return new SysUserRequest(this);
  }

  @Override
  public void clear() {
    this.userIds = null;
  }

  public int getUserIdsSize() {
    return (this.userIds == null) ? 0 : this.userIds.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.Long> getUserIdsIterator() {
    return (this.userIds == null) ? null : this.userIds.iterator();
  }

  public void addToUserIds(long elem) {
    if (this.userIds == null) {
      this.userIds = new java.util.ArrayList<java.lang.Long>();
    }
    this.userIds.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.Long> getUserIds() {
    return this.userIds;
  }

  public SysUserRequest setUserIds(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.Long> userIds) {
    this.userIds = userIds;
    return this;
  }

  public void unsetUserIds() {
    this.userIds = null;
  }

  /** Returns true if field userIds is set (has been assigned a value) and false otherwise */
  public boolean isSetUserIds() {
    return this.userIds != null;
  }

  public void setUserIdsIsSet(boolean value) {
    if (!value) {
      this.userIds = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case USER_IDS:
      if (value == null) {
        unsetUserIds();
      } else {
        setUserIds((java.util.List<java.lang.Long>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_IDS:
      return getUserIds();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case USER_IDS:
      return isSetUserIds();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof SysUserRequest)
      return this.equals((SysUserRequest)that);
    return false;
  }

  public boolean equals(SysUserRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_userIds = true && this.isSetUserIds();
    boolean that_present_userIds = true && that.isSetUserIds();
    if (this_present_userIds || that_present_userIds) {
      if (!(this_present_userIds && that_present_userIds))
        return false;
      if (!this.userIds.equals(that.userIds))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetUserIds()) ? 131071 : 524287);
    if (isSetUserIds())
      hashCode = hashCode * 8191 + userIds.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(SysUserRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetUserIds(), other.isSetUserIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userIds, other.userIds);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SysUserRequest(");
    boolean first = true;

    sb.append("userIds:");
    if (this.userIds == null) {
      sb.append("null");
    } else {
      sb.append(this.userIds);
    }
    first = false;
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

  private static class SysUserRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SysUserRequestStandardScheme getScheme() {
      return new SysUserRequestStandardScheme();
    }
  }

  private static class SysUserRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<SysUserRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SysUserRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.userIds = new java.util.ArrayList<java.lang.Long>(_list0.size);
                long _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readI64();
                  struct.userIds.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setUserIdsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SysUserRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.userIds != null) {
        oprot.writeFieldBegin(USER_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.userIds.size()));
          for (long _iter3 : struct.userIds)
          {
            oprot.writeI64(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SysUserRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SysUserRequestTupleScheme getScheme() {
      return new SysUserRequestTupleScheme();
    }
  }

  private static class SysUserRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<SysUserRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SysUserRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetUserIds()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetUserIds()) {
        {
          oprot.writeI32(struct.userIds.size());
          for (long _iter4 : struct.userIds)
          {
            oprot.writeI64(_iter4);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SysUserRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list5 = iprot.readListBegin(org.apache.thrift.protocol.TType.I64);
          struct.userIds = new java.util.ArrayList<java.lang.Long>(_list5.size);
          long _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readI64();
            struct.userIds.add(_elem6);
          }
        }
        struct.setUserIdsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

