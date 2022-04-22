/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.people.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-21")
public class SendPeopleQueueBatchRequest implements org.apache.thrift.TBase<SendPeopleQueueBatchRequest, SendPeopleQueueBatchRequest._Fields>, java.io.Serializable, Cloneable, Comparable<SendPeopleQueueBatchRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SendPeopleQueueBatchRequest");

  private static final org.apache.thrift.protocol.TField SEND_PEOPLE_QUEUE_REQUESTS_FIELD_DESC = new org.apache.thrift.protocol.TField("sendPeopleQueueRequests", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField CONFIG_KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("configKey", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SendPeopleQueueBatchRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SendPeopleQueueBatchRequestTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<SendPeopleQueueRequest> sendPeopleQueueRequests; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String configKey; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SEND_PEOPLE_QUEUE_REQUESTS((short)1, "sendPeopleQueueRequests"),
    CONFIG_KEY((short)2, "configKey");

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
        case 1: // SEND_PEOPLE_QUEUE_REQUESTS
          return SEND_PEOPLE_QUEUE_REQUESTS;
        case 2: // CONFIG_KEY
          return CONFIG_KEY;
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
    tmpMap.put(_Fields.SEND_PEOPLE_QUEUE_REQUESTS, new org.apache.thrift.meta_data.FieldMetaData("sendPeopleQueueRequests", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, SendPeopleQueueRequest.class))));
    tmpMap.put(_Fields.CONFIG_KEY, new org.apache.thrift.meta_data.FieldMetaData("configKey", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SendPeopleQueueBatchRequest.class, metaDataMap);
  }

  public SendPeopleQueueBatchRequest() {
  }

  public SendPeopleQueueBatchRequest(
    java.util.List<SendPeopleQueueRequest> sendPeopleQueueRequests,
    java.lang.String configKey)
  {
    this();
    this.sendPeopleQueueRequests = sendPeopleQueueRequests;
    this.configKey = configKey;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SendPeopleQueueBatchRequest(SendPeopleQueueBatchRequest other) {
    if (other.isSetSendPeopleQueueRequests()) {
      java.util.List<SendPeopleQueueRequest> __this__sendPeopleQueueRequests = new java.util.ArrayList<SendPeopleQueueRequest>(other.sendPeopleQueueRequests.size());
      for (SendPeopleQueueRequest other_element : other.sendPeopleQueueRequests) {
        __this__sendPeopleQueueRequests.add(new SendPeopleQueueRequest(other_element));
      }
      this.sendPeopleQueueRequests = __this__sendPeopleQueueRequests;
    }
    if (other.isSetConfigKey()) {
      this.configKey = other.configKey;
    }
  }

  public SendPeopleQueueBatchRequest deepCopy() {
    return new SendPeopleQueueBatchRequest(this);
  }

  @Override
  public void clear() {
    this.sendPeopleQueueRequests = null;
    this.configKey = null;
  }

  public int getSendPeopleQueueRequestsSize() {
    return (this.sendPeopleQueueRequests == null) ? 0 : this.sendPeopleQueueRequests.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<SendPeopleQueueRequest> getSendPeopleQueueRequestsIterator() {
    return (this.sendPeopleQueueRequests == null) ? null : this.sendPeopleQueueRequests.iterator();
  }

  public void addToSendPeopleQueueRequests(SendPeopleQueueRequest elem) {
    if (this.sendPeopleQueueRequests == null) {
      this.sendPeopleQueueRequests = new java.util.ArrayList<SendPeopleQueueRequest>();
    }
    this.sendPeopleQueueRequests.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<SendPeopleQueueRequest> getSendPeopleQueueRequests() {
    return this.sendPeopleQueueRequests;
  }

  public SendPeopleQueueBatchRequest setSendPeopleQueueRequests(@org.apache.thrift.annotation.Nullable java.util.List<SendPeopleQueueRequest> sendPeopleQueueRequests) {
    this.sendPeopleQueueRequests = sendPeopleQueueRequests;
    return this;
  }

  public void unsetSendPeopleQueueRequests() {
    this.sendPeopleQueueRequests = null;
  }

  /** Returns true if field sendPeopleQueueRequests is set (has been assigned a value) and false otherwise */
  public boolean isSetSendPeopleQueueRequests() {
    return this.sendPeopleQueueRequests != null;
  }

  public void setSendPeopleQueueRequestsIsSet(boolean value) {
    if (!value) {
      this.sendPeopleQueueRequests = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getConfigKey() {
    return this.configKey;
  }

  public SendPeopleQueueBatchRequest setConfigKey(@org.apache.thrift.annotation.Nullable java.lang.String configKey) {
    this.configKey = configKey;
    return this;
  }

  public void unsetConfigKey() {
    this.configKey = null;
  }

  /** Returns true if field configKey is set (has been assigned a value) and false otherwise */
  public boolean isSetConfigKey() {
    return this.configKey != null;
  }

  public void setConfigKeyIsSet(boolean value) {
    if (!value) {
      this.configKey = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case SEND_PEOPLE_QUEUE_REQUESTS:
      if (value == null) {
        unsetSendPeopleQueueRequests();
      } else {
        setSendPeopleQueueRequests((java.util.List<SendPeopleQueueRequest>)value);
      }
      break;

    case CONFIG_KEY:
      if (value == null) {
        unsetConfigKey();
      } else {
        setConfigKey((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SEND_PEOPLE_QUEUE_REQUESTS:
      return getSendPeopleQueueRequests();

    case CONFIG_KEY:
      return getConfigKey();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SEND_PEOPLE_QUEUE_REQUESTS:
      return isSetSendPeopleQueueRequests();
    case CONFIG_KEY:
      return isSetConfigKey();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof SendPeopleQueueBatchRequest)
      return this.equals((SendPeopleQueueBatchRequest)that);
    return false;
  }

  public boolean equals(SendPeopleQueueBatchRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_sendPeopleQueueRequests = true && this.isSetSendPeopleQueueRequests();
    boolean that_present_sendPeopleQueueRequests = true && that.isSetSendPeopleQueueRequests();
    if (this_present_sendPeopleQueueRequests || that_present_sendPeopleQueueRequests) {
      if (!(this_present_sendPeopleQueueRequests && that_present_sendPeopleQueueRequests))
        return false;
      if (!this.sendPeopleQueueRequests.equals(that.sendPeopleQueueRequests))
        return false;
    }

    boolean this_present_configKey = true && this.isSetConfigKey();
    boolean that_present_configKey = true && that.isSetConfigKey();
    if (this_present_configKey || that_present_configKey) {
      if (!(this_present_configKey && that_present_configKey))
        return false;
      if (!this.configKey.equals(that.configKey))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetSendPeopleQueueRequests()) ? 131071 : 524287);
    if (isSetSendPeopleQueueRequests())
      hashCode = hashCode * 8191 + sendPeopleQueueRequests.hashCode();

    hashCode = hashCode * 8191 + ((isSetConfigKey()) ? 131071 : 524287);
    if (isSetConfigKey())
      hashCode = hashCode * 8191 + configKey.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(SendPeopleQueueBatchRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetSendPeopleQueueRequests(), other.isSetSendPeopleQueueRequests());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSendPeopleQueueRequests()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sendPeopleQueueRequests, other.sendPeopleQueueRequests);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetConfigKey(), other.isSetConfigKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetConfigKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.configKey, other.configKey);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SendPeopleQueueBatchRequest(");
    boolean first = true;

    sb.append("sendPeopleQueueRequests:");
    if (this.sendPeopleQueueRequests == null) {
      sb.append("null");
    } else {
      sb.append(this.sendPeopleQueueRequests);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("configKey:");
    if (this.configKey == null) {
      sb.append("null");
    } else {
      sb.append(this.configKey);
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

  private static class SendPeopleQueueBatchRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SendPeopleQueueBatchRequestStandardScheme getScheme() {
      return new SendPeopleQueueBatchRequestStandardScheme();
    }
  }

  private static class SendPeopleQueueBatchRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<SendPeopleQueueBatchRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SendPeopleQueueBatchRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SEND_PEOPLE_QUEUE_REQUESTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.sendPeopleQueueRequests = new java.util.ArrayList<SendPeopleQueueRequest>(_list0.size);
                @org.apache.thrift.annotation.Nullable SendPeopleQueueRequest _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new SendPeopleQueueRequest();
                  _elem1.read(iprot);
                  struct.sendPeopleQueueRequests.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSendPeopleQueueRequestsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CONFIG_KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.configKey = iprot.readString();
              struct.setConfigKeyIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SendPeopleQueueBatchRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.sendPeopleQueueRequests != null) {
        oprot.writeFieldBegin(SEND_PEOPLE_QUEUE_REQUESTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.sendPeopleQueueRequests.size()));
          for (SendPeopleQueueRequest _iter3 : struct.sendPeopleQueueRequests)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.configKey != null) {
        oprot.writeFieldBegin(CONFIG_KEY_FIELD_DESC);
        oprot.writeString(struct.configKey);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SendPeopleQueueBatchRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SendPeopleQueueBatchRequestTupleScheme getScheme() {
      return new SendPeopleQueueBatchRequestTupleScheme();
    }
  }

  private static class SendPeopleQueueBatchRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<SendPeopleQueueBatchRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SendPeopleQueueBatchRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSendPeopleQueueRequests()) {
        optionals.set(0);
      }
      if (struct.isSetConfigKey()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSendPeopleQueueRequests()) {
        {
          oprot.writeI32(struct.sendPeopleQueueRequests.size());
          for (SendPeopleQueueRequest _iter4 : struct.sendPeopleQueueRequests)
          {
            _iter4.write(oprot);
          }
        }
      }
      if (struct.isSetConfigKey()) {
        oprot.writeString(struct.configKey);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SendPeopleQueueBatchRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list5 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRUCT);
          struct.sendPeopleQueueRequests = new java.util.ArrayList<SendPeopleQueueRequest>(_list5.size);
          @org.apache.thrift.annotation.Nullable SendPeopleQueueRequest _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new SendPeopleQueueRequest();
            _elem6.read(iprot);
            struct.sendPeopleQueueRequests.add(_elem6);
          }
        }
        struct.setSendPeopleQueueRequestsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.configKey = iprot.readString();
        struct.setConfigKeyIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

