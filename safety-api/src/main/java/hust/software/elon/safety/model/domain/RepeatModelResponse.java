/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.model.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-23")
public class RepeatModelResponse implements org.apache.thrift.TBase<RepeatModelResponse, RepeatModelResponse._Fields>, java.io.Serializable, Cloneable, Comparable<RepeatModelResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RepeatModelResponse");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField REPEAT_SEGMENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("repeatSegments", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField MODEL_SCORE_FIELD_DESC = new org.apache.thrift.protocol.TField("modelScore", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField STATUS_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("statusCode", org.apache.thrift.protocol.TType.I32, (short)255);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RepeatModelResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RepeatModelResponseTupleSchemeFactory();

  public long id; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<RepeatSegment> repeatSegments; // required
  public double modelScore; // required
  /**
   * 
   * @see hust.software.elon.safety.common.domain.StatusCode
   */
  public @org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.StatusCode statusCode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    REPEAT_SEGMENTS((short)2, "repeatSegments"),
    MODEL_SCORE((short)3, "modelScore"),
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
        case 1: // ID
          return ID;
        case 2: // REPEAT_SEGMENTS
          return REPEAT_SEGMENTS;
        case 3: // MODEL_SCORE
          return MODEL_SCORE;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __MODELSCORE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.STATUS_CODE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.REPEAT_SEGMENTS, new org.apache.thrift.meta_data.FieldMetaData("repeatSegments", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RepeatSegment.class))));
    tmpMap.put(_Fields.MODEL_SCORE, new org.apache.thrift.meta_data.FieldMetaData("modelScore", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.STATUS_CODE, new org.apache.thrift.meta_data.FieldMetaData("statusCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, hust.software.elon.safety.common.domain.StatusCode.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RepeatModelResponse.class, metaDataMap);
  }

  public RepeatModelResponse() {
    this.statusCode = hust.software.elon.safety.common.domain.StatusCode.SUCCESS;

  }

  public RepeatModelResponse(
    long id,
    java.util.List<RepeatSegment> repeatSegments,
    double modelScore)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.repeatSegments = repeatSegments;
    this.modelScore = modelScore;
    setModelScoreIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RepeatModelResponse(RepeatModelResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetRepeatSegments()) {
      java.util.List<RepeatSegment> __this__repeatSegments = new java.util.ArrayList<RepeatSegment>(other.repeatSegments.size());
      for (RepeatSegment other_element : other.repeatSegments) {
        __this__repeatSegments.add(new RepeatSegment(other_element));
      }
      this.repeatSegments = __this__repeatSegments;
    }
    this.modelScore = other.modelScore;
    if (other.isSetStatusCode()) {
      this.statusCode = other.statusCode;
    }
  }

  public RepeatModelResponse deepCopy() {
    return new RepeatModelResponse(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.repeatSegments = null;
    setModelScoreIsSet(false);
    this.modelScore = 0.0;
    this.statusCode = hust.software.elon.safety.common.domain.StatusCode.SUCCESS;

  }

  public long getId() {
    return this.id;
  }

  public RepeatModelResponse setId(long id) {
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

  public int getRepeatSegmentsSize() {
    return (this.repeatSegments == null) ? 0 : this.repeatSegments.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<RepeatSegment> getRepeatSegmentsIterator() {
    return (this.repeatSegments == null) ? null : this.repeatSegments.iterator();
  }

  public void addToRepeatSegments(RepeatSegment elem) {
    if (this.repeatSegments == null) {
      this.repeatSegments = new java.util.ArrayList<RepeatSegment>();
    }
    this.repeatSegments.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<RepeatSegment> getRepeatSegments() {
    return this.repeatSegments;
  }

  public RepeatModelResponse setRepeatSegments(@org.apache.thrift.annotation.Nullable java.util.List<RepeatSegment> repeatSegments) {
    this.repeatSegments = repeatSegments;
    return this;
  }

  public void unsetRepeatSegments() {
    this.repeatSegments = null;
  }

  /** Returns true if field repeatSegments is set (has been assigned a value) and false otherwise */
  public boolean isSetRepeatSegments() {
    return this.repeatSegments != null;
  }

  public void setRepeatSegmentsIsSet(boolean value) {
    if (!value) {
      this.repeatSegments = null;
    }
  }

  public double getModelScore() {
    return this.modelScore;
  }

  public RepeatModelResponse setModelScore(double modelScore) {
    this.modelScore = modelScore;
    setModelScoreIsSet(true);
    return this;
  }

  public void unsetModelScore() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MODELSCORE_ISSET_ID);
  }

  /** Returns true if field modelScore is set (has been assigned a value) and false otherwise */
  public boolean isSetModelScore() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MODELSCORE_ISSET_ID);
  }

  public void setModelScoreIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MODELSCORE_ISSET_ID, value);
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
  public RepeatModelResponse setStatusCode(@org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.StatusCode statusCode) {
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
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.Long)value);
      }
      break;

    case REPEAT_SEGMENTS:
      if (value == null) {
        unsetRepeatSegments();
      } else {
        setRepeatSegments((java.util.List<RepeatSegment>)value);
      }
      break;

    case MODEL_SCORE:
      if (value == null) {
        unsetModelScore();
      } else {
        setModelScore((java.lang.Double)value);
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
    case ID:
      return getId();

    case REPEAT_SEGMENTS:
      return getRepeatSegments();

    case MODEL_SCORE:
      return getModelScore();

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
    case ID:
      return isSetId();
    case REPEAT_SEGMENTS:
      return isSetRepeatSegments();
    case MODEL_SCORE:
      return isSetModelScore();
    case STATUS_CODE:
      return isSetStatusCode();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof RepeatModelResponse)
      return this.equals((RepeatModelResponse)that);
    return false;
  }

  public boolean equals(RepeatModelResponse that) {
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

    boolean this_present_repeatSegments = true && this.isSetRepeatSegments();
    boolean that_present_repeatSegments = true && that.isSetRepeatSegments();
    if (this_present_repeatSegments || that_present_repeatSegments) {
      if (!(this_present_repeatSegments && that_present_repeatSegments))
        return false;
      if (!this.repeatSegments.equals(that.repeatSegments))
        return false;
    }

    boolean this_present_modelScore = true;
    boolean that_present_modelScore = true;
    if (this_present_modelScore || that_present_modelScore) {
      if (!(this_present_modelScore && that_present_modelScore))
        return false;
      if (this.modelScore != that.modelScore)
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

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

    hashCode = hashCode * 8191 + ((isSetRepeatSegments()) ? 131071 : 524287);
    if (isSetRepeatSegments())
      hashCode = hashCode * 8191 + repeatSegments.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(modelScore);

    hashCode = hashCode * 8191 + ((isSetStatusCode()) ? 131071 : 524287);
    if (isSetStatusCode())
      hashCode = hashCode * 8191 + statusCode.getValue();

    return hashCode;
  }

  @Override
  public int compareTo(RepeatModelResponse other) {
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
    lastComparison = java.lang.Boolean.compare(isSetRepeatSegments(), other.isSetRepeatSegments());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRepeatSegments()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.repeatSegments, other.repeatSegments);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetModelScore(), other.isSetModelScore());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetModelScore()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.modelScore, other.modelScore);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RepeatModelResponse(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("repeatSegments:");
    if (this.repeatSegments == null) {
      sb.append("null");
    } else {
      sb.append(this.repeatSegments);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("modelScore:");
    sb.append(this.modelScore);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RepeatModelResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RepeatModelResponseStandardScheme getScheme() {
      return new RepeatModelResponseStandardScheme();
    }
  }

  private static class RepeatModelResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<RepeatModelResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RepeatModelResponse struct) throws org.apache.thrift.TException {
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
          case 2: // REPEAT_SEGMENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.repeatSegments = new java.util.ArrayList<RepeatSegment>(_list8.size);
                @org.apache.thrift.annotation.Nullable RepeatSegment _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new RepeatSegment();
                  _elem9.read(iprot);
                  struct.repeatSegments.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setRepeatSegmentsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MODEL_SCORE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.modelScore = iprot.readDouble();
              struct.setModelScoreIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RepeatModelResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.repeatSegments != null) {
        oprot.writeFieldBegin(REPEAT_SEGMENTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.repeatSegments.size()));
          for (RepeatSegment _iter11 : struct.repeatSegments)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(MODEL_SCORE_FIELD_DESC);
      oprot.writeDouble(struct.modelScore);
      oprot.writeFieldEnd();
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

  private static class RepeatModelResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RepeatModelResponseTupleScheme getScheme() {
      return new RepeatModelResponseTupleScheme();
    }
  }

  private static class RepeatModelResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<RepeatModelResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RepeatModelResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetRepeatSegments()) {
        optionals.set(1);
      }
      if (struct.isSetModelScore()) {
        optionals.set(2);
      }
      if (struct.isSetStatusCode()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetRepeatSegments()) {
        {
          oprot.writeI32(struct.repeatSegments.size());
          for (RepeatSegment _iter12 : struct.repeatSegments)
          {
            _iter12.write(oprot);
          }
        }
      }
      if (struct.isSetModelScore()) {
        oprot.writeDouble(struct.modelScore);
      }
      if (struct.isSetStatusCode()) {
        oprot.writeI32(struct.statusCode.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RepeatModelResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRUCT);
          struct.repeatSegments = new java.util.ArrayList<RepeatSegment>(_list13.size);
          @org.apache.thrift.annotation.Nullable RepeatSegment _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new RepeatSegment();
            _elem14.read(iprot);
            struct.repeatSegments.add(_elem14);
          }
        }
        struct.setRepeatSegmentsIsSet(true);
      }
      if (incoming.get(2)) {
        struct.modelScore = iprot.readDouble();
        struct.setModelScoreIsSet(true);
      }
      if (incoming.get(3)) {
        struct.statusCode = hust.software.elon.safety.common.domain.StatusCode.findByValue(iprot.readI32());
        struct.setStatusCodeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

