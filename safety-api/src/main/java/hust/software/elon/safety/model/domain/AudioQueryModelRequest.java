/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.model.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-20")
public class AudioQueryModelRequest implements org.apache.thrift.TBase<AudioQueryModelRequest, AudioQueryModelRequest._Fields>, java.io.Serializable, Cloneable, Comparable<AudioQueryModelRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AudioQueryModelRequest");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField RISK_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("riskType", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField THRESHOLD_FIELD_DESC = new org.apache.thrift.protocol.TField("threshold", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("token", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new AudioQueryModelRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new AudioQueryModelRequestTupleSchemeFactory();

  public long id; // required
  /**
   * 
   * @see RiskType
   */
  public @org.apache.thrift.annotation.Nullable RiskType riskType; // optional
  public double threshold; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String token; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    /**
     * 
     * @see RiskType
     */
    RISK_TYPE((short)2, "riskType"),
    THRESHOLD((short)3, "threshold"),
    TOKEN((short)4, "token");

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
        case 1: // ID
          return ID;
        case 2: // RISK_TYPE
          return RISK_TYPE;
        case 3: // THRESHOLD
          return THRESHOLD;
        case 4: // TOKEN
          return TOKEN;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __THRESHOLD_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.RISK_TYPE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RISK_TYPE, new org.apache.thrift.meta_data.FieldMetaData("riskType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, RiskType.class)));
    tmpMap.put(_Fields.THRESHOLD, new org.apache.thrift.meta_data.FieldMetaData("threshold", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.TOKEN, new org.apache.thrift.meta_data.FieldMetaData("token", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AudioQueryModelRequest.class, metaDataMap);
  }

  public AudioQueryModelRequest() {
    this.riskType = hust.software.elon.safety.model.domain.RiskType.MODEL_NONE;

  }

  public AudioQueryModelRequest(
    long id,
    double threshold,
    java.lang.String token)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.threshold = threshold;
    setThresholdIsSet(true);
    this.token = token;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AudioQueryModelRequest(AudioQueryModelRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetRiskType()) {
      this.riskType = other.riskType;
    }
    this.threshold = other.threshold;
    if (other.isSetToken()) {
      this.token = other.token;
    }
  }

  public AudioQueryModelRequest deepCopy() {
    return new AudioQueryModelRequest(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.riskType = hust.software.elon.safety.model.domain.RiskType.MODEL_NONE;

    setThresholdIsSet(false);
    this.threshold = 0.0;
    this.token = null;
  }

  public long getId() {
    return this.id;
  }

  public AudioQueryModelRequest setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  /**
   * 
   * @see RiskType
   */
  @org.apache.thrift.annotation.Nullable
  public RiskType getRiskType() {
    return this.riskType;
  }

  /**
   * 
   * @see RiskType
   */
  public AudioQueryModelRequest setRiskType(@org.apache.thrift.annotation.Nullable RiskType riskType) {
    this.riskType = riskType;
    return this;
  }

  public void unsetRiskType() {
    this.riskType = null;
  }

  /** Returns true if field riskType is set (has been assigned a value) and false otherwise */
  public boolean isSetRiskType() {
    return this.riskType != null;
  }

  public void setRiskTypeIsSet(boolean value) {
    if (!value) {
      this.riskType = null;
    }
  }

  public double getThreshold() {
    return this.threshold;
  }

  public AudioQueryModelRequest setThreshold(double threshold) {
    this.threshold = threshold;
    setThresholdIsSet(true);
    return this;
  }

  public void unsetThreshold() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __THRESHOLD_ISSET_ID);
  }

  /** Returns true if field threshold is set (has been assigned a value) and false otherwise */
  public boolean isSetThreshold() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __THRESHOLD_ISSET_ID);
  }

  public void setThresholdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __THRESHOLD_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getToken() {
    return this.token;
  }

  public AudioQueryModelRequest setToken(@org.apache.thrift.annotation.Nullable java.lang.String token) {
    this.token = token;
    return this;
  }

  public void unsetToken() {
    this.token = null;
  }

  /** Returns true if field token is set (has been assigned a value) and false otherwise */
  public boolean isSetToken() {
    return this.token != null;
  }

  public void setTokenIsSet(boolean value) {
    if (!value) {
      this.token = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.Long)value);
      }
      break;

    case RISK_TYPE:
      if (value == null) {
        unsetRiskType();
      } else {
        setRiskType((RiskType)value);
      }
      break;

    case THRESHOLD:
      if (value == null) {
        unsetThreshold();
      } else {
        setThreshold((java.lang.Double)value);
      }
      break;

    case TOKEN:
      if (value == null) {
        unsetToken();
      } else {
        setToken((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case RISK_TYPE:
      return getRiskType();

    case THRESHOLD:
      return getThreshold();

    case TOKEN:
      return getToken();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case RISK_TYPE:
      return isSetRiskType();
    case THRESHOLD:
      return isSetThreshold();
    case TOKEN:
      return isSetToken();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof AudioQueryModelRequest)
      return this.equals((AudioQueryModelRequest)that);
    return false;
  }

  public boolean equals(AudioQueryModelRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_riskType = true && this.isSetRiskType();
    boolean that_present_riskType = true && that.isSetRiskType();
    if (this_present_riskType || that_present_riskType) {
      if (!(this_present_riskType && that_present_riskType))
        return false;
      if (!this.riskType.equals(that.riskType))
        return false;
    }

    boolean this_present_threshold = true;
    boolean that_present_threshold = true;
    if (this_present_threshold || that_present_threshold) {
      if (!(this_present_threshold && that_present_threshold))
        return false;
      if (this.threshold != that.threshold)
        return false;
    }

    boolean this_present_token = true && this.isSetToken();
    boolean that_present_token = true && that.isSetToken();
    if (this_present_token || that_present_token) {
      if (!(this_present_token && that_present_token))
        return false;
      if (!this.token.equals(that.token))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

    hashCode = hashCode * 8191 + ((isSetRiskType()) ? 131071 : 524287);
    if (isSetRiskType())
      hashCode = hashCode * 8191 + riskType.getValue();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(threshold);

    hashCode = hashCode * 8191 + ((isSetToken()) ? 131071 : 524287);
    if (isSetToken())
      hashCode = hashCode * 8191 + token.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(AudioQueryModelRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetId(), other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetRiskType(), other.isSetRiskType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRiskType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.riskType, other.riskType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetThreshold(), other.isSetThreshold());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetThreshold()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.threshold, other.threshold);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetToken(), other.isSetToken());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetToken()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.token, other.token);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("AudioQueryModelRequest(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (isSetRiskType()) {
      if (!first) sb.append(", ");
      sb.append("riskType:");
      if (this.riskType == null) {
        sb.append("null");
      } else {
        sb.append(this.riskType);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("threshold:");
    sb.append(this.threshold);
    first = false;
    if (!first) sb.append(", ");
    sb.append("token:");
    if (this.token == null) {
      sb.append("null");
    } else {
      sb.append(this.token);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'id' because it's a primitive and you chose the non-beans generator.
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AudioQueryModelRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AudioQueryModelRequestStandardScheme getScheme() {
      return new AudioQueryModelRequestStandardScheme();
    }
  }

  private static class AudioQueryModelRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<AudioQueryModelRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AudioQueryModelRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RISK_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.riskType = hust.software.elon.safety.model.domain.RiskType.findByValue(iprot.readI32());
              struct.setRiskTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // THRESHOLD
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.threshold = iprot.readDouble();
              struct.setThresholdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TOKEN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.token = iprot.readString();
              struct.setTokenIsSet(true);
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
      if (!struct.isSetId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, AudioQueryModelRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.riskType != null) {
        if (struct.isSetRiskType()) {
          oprot.writeFieldBegin(RISK_TYPE_FIELD_DESC);
          oprot.writeI32(struct.riskType.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldBegin(THRESHOLD_FIELD_DESC);
      oprot.writeDouble(struct.threshold);
      oprot.writeFieldEnd();
      if (struct.token != null) {
        oprot.writeFieldBegin(TOKEN_FIELD_DESC);
        oprot.writeString(struct.token);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AudioQueryModelRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AudioQueryModelRequestTupleScheme getScheme() {
      return new AudioQueryModelRequestTupleScheme();
    }
  }

  private static class AudioQueryModelRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<AudioQueryModelRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AudioQueryModelRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.id);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRiskType()) {
        optionals.set(0);
      }
      if (struct.isSetThreshold()) {
        optionals.set(1);
      }
      if (struct.isSetToken()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRiskType()) {
        oprot.writeI32(struct.riskType.getValue());
      }
      if (struct.isSetThreshold()) {
        oprot.writeDouble(struct.threshold);
      }
      if (struct.isSetToken()) {
        oprot.writeString(struct.token);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AudioQueryModelRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.id = iprot.readI64();
      struct.setIdIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.riskType = hust.software.elon.safety.model.domain.RiskType.findByValue(iprot.readI32());
        struct.setRiskTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.threshold = iprot.readDouble();
        struct.setThresholdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.token = iprot.readString();
        struct.setTokenIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

