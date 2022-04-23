/**
 * Autogenerated by Thrift Compiler (0.16.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package hust.software.elon.safety.model.domain;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.16.0)", date = "2022-04-22")
public class AsrModelResponse implements org.apache.thrift.TBase<AsrModelResponse, AsrModelResponse._Fields>, java.io.Serializable, Cloneable, Comparable<AsrModelResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AsrModelResponse");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField ASR_FIELD_DESC = new org.apache.thrift.protocol.TField("asr", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SPELL_FIELD_DESC = new org.apache.thrift.protocol.TField("spell", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField STATUS_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("statusCode", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new AsrModelResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new AsrModelResponseTupleSchemeFactory();

  public long id; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String asr; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> spell; // required
  /**
   * 
   * @see hust.software.elon.safety.common.domain.StatusCode
   */
  public @org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.StatusCode statusCode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    ASR((short)2, "asr"),
    SPELL((short)3, "spell"),
    /**
     * 
     * @see hust.software.elon.safety.common.domain.StatusCode
     */
    STATUS_CODE((short)4, "statusCode");

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
        case 2: // ASR
          return ASR;
        case 3: // SPELL
          return SPELL;
        case 4: // STATUS_CODE
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
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.STATUS_CODE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ASR, new org.apache.thrift.meta_data.FieldMetaData("asr", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SPELL, new org.apache.thrift.meta_data.FieldMetaData("spell", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.STATUS_CODE, new org.apache.thrift.meta_data.FieldMetaData("statusCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, hust.software.elon.safety.common.domain.StatusCode.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AsrModelResponse.class, metaDataMap);
  }

  public AsrModelResponse() {
    this.statusCode = hust.software.elon.safety.common.domain.StatusCode.SUCCESS;

  }

  public AsrModelResponse(
    long id,
    java.lang.String asr,
    java.util.List<java.lang.String> spell)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.asr = asr;
    this.spell = spell;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AsrModelResponse(AsrModelResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetAsr()) {
      this.asr = other.asr;
    }
    if (other.isSetSpell()) {
      java.util.List<java.lang.String> __this__spell = new java.util.ArrayList<java.lang.String>(other.spell);
      this.spell = __this__spell;
    }
    if (other.isSetStatusCode()) {
      this.statusCode = other.statusCode;
    }
  }

  public AsrModelResponse deepCopy() {
    return new AsrModelResponse(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.asr = null;
    this.spell = null;
    this.statusCode = hust.software.elon.safety.common.domain.StatusCode.SUCCESS;

  }

  public long getId() {
    return this.id;
  }

  public AsrModelResponse setId(long id) {
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

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getAsr() {
    return this.asr;
  }

  public AsrModelResponse setAsr(@org.apache.thrift.annotation.Nullable java.lang.String asr) {
    this.asr = asr;
    return this;
  }

  public void unsetAsr() {
    this.asr = null;
  }

  /** Returns true if field asr is set (has been assigned a value) and false otherwise */
  public boolean isSetAsr() {
    return this.asr != null;
  }

  public void setAsrIsSet(boolean value) {
    if (!value) {
      this.asr = null;
    }
  }

  public int getSpellSize() {
    return (this.spell == null) ? 0 : this.spell.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.String> getSpellIterator() {
    return (this.spell == null) ? null : this.spell.iterator();
  }

  public void addToSpell(java.lang.String elem) {
    if (this.spell == null) {
      this.spell = new java.util.ArrayList<java.lang.String>();
    }
    this.spell.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.String> getSpell() {
    return this.spell;
  }

  public AsrModelResponse setSpell(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> spell) {
    this.spell = spell;
    return this;
  }

  public void unsetSpell() {
    this.spell = null;
  }

  /** Returns true if field spell is set (has been assigned a value) and false otherwise */
  public boolean isSetSpell() {
    return this.spell != null;
  }

  public void setSpellIsSet(boolean value) {
    if (!value) {
      this.spell = null;
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
  public AsrModelResponse setStatusCode(@org.apache.thrift.annotation.Nullable hust.software.elon.safety.common.domain.StatusCode statusCode) {
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

    case ASR:
      if (value == null) {
        unsetAsr();
      } else {
        setAsr((java.lang.String)value);
      }
      break;

    case SPELL:
      if (value == null) {
        unsetSpell();
      } else {
        setSpell((java.util.List<java.lang.String>)value);
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

    case ASR:
      return getAsr();

    case SPELL:
      return getSpell();

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
    case ASR:
      return isSetAsr();
    case SPELL:
      return isSetSpell();
    case STATUS_CODE:
      return isSetStatusCode();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof AsrModelResponse)
      return this.equals((AsrModelResponse)that);
    return false;
  }

  public boolean equals(AsrModelResponse that) {
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

    boolean this_present_asr = true && this.isSetAsr();
    boolean that_present_asr = true && that.isSetAsr();
    if (this_present_asr || that_present_asr) {
      if (!(this_present_asr && that_present_asr))
        return false;
      if (!this.asr.equals(that.asr))
        return false;
    }

    boolean this_present_spell = true && this.isSetSpell();
    boolean that_present_spell = true && that.isSetSpell();
    if (this_present_spell || that_present_spell) {
      if (!(this_present_spell && that_present_spell))
        return false;
      if (!this.spell.equals(that.spell))
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

    hashCode = hashCode * 8191 + ((isSetAsr()) ? 131071 : 524287);
    if (isSetAsr())
      hashCode = hashCode * 8191 + asr.hashCode();

    hashCode = hashCode * 8191 + ((isSetSpell()) ? 131071 : 524287);
    if (isSetSpell())
      hashCode = hashCode * 8191 + spell.hashCode();

    hashCode = hashCode * 8191 + ((isSetStatusCode()) ? 131071 : 524287);
    if (isSetStatusCode())
      hashCode = hashCode * 8191 + statusCode.getValue();

    return hashCode;
  }

  @Override
  public int compareTo(AsrModelResponse other) {
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
    lastComparison = java.lang.Boolean.compare(isSetAsr(), other.isSetAsr());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAsr()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.asr, other.asr);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetSpell(), other.isSetSpell());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSpell()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.spell, other.spell);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("AsrModelResponse(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("asr:");
    if (this.asr == null) {
      sb.append("null");
    } else {
      sb.append(this.asr);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("spell:");
    if (this.spell == null) {
      sb.append("null");
    } else {
      sb.append(this.spell);
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

  private static class AsrModelResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AsrModelResponseStandardScheme getScheme() {
      return new AsrModelResponseStandardScheme();
    }
  }

  private static class AsrModelResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<AsrModelResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AsrModelResponse struct) throws org.apache.thrift.TException {
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
          case 2: // ASR
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.asr = iprot.readString();
              struct.setAsrIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SPELL
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.spell = new java.util.ArrayList<java.lang.String>(_list0.size);
                @org.apache.thrift.annotation.Nullable java.lang.String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.spell.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSpellIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // STATUS_CODE
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
      if (!struct.isSetId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, AsrModelResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.asr != null) {
        oprot.writeFieldBegin(ASR_FIELD_DESC);
        oprot.writeString(struct.asr);
        oprot.writeFieldEnd();
      }
      if (struct.spell != null) {
        oprot.writeFieldBegin(SPELL_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.spell.size()));
          for (java.lang.String _iter3 : struct.spell)
          {
            oprot.writeString(_iter3);
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

  private static class AsrModelResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AsrModelResponseTupleScheme getScheme() {
      return new AsrModelResponseTupleScheme();
    }
  }

  private static class AsrModelResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<AsrModelResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AsrModelResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.id);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAsr()) {
        optionals.set(0);
      }
      if (struct.isSetSpell()) {
        optionals.set(1);
      }
      if (struct.isSetStatusCode()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetAsr()) {
        oprot.writeString(struct.asr);
      }
      if (struct.isSetSpell()) {
        {
          oprot.writeI32(struct.spell.size());
          for (java.lang.String _iter4 : struct.spell)
          {
            oprot.writeString(_iter4);
          }
        }
      }
      if (struct.isSetStatusCode()) {
        oprot.writeI32(struct.statusCode.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AsrModelResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.id = iprot.readI64();
      struct.setIdIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.asr = iprot.readString();
        struct.setAsrIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list5 = iprot.readListBegin(org.apache.thrift.protocol.TType.STRING);
          struct.spell = new java.util.ArrayList<java.lang.String>(_list5.size);
          @org.apache.thrift.annotation.Nullable java.lang.String _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readString();
            struct.spell.add(_elem6);
          }
        }
        struct.setSpellIsSet(true);
      }
      if (incoming.get(2)) {
        struct.statusCode = hust.software.elon.safety.common.domain.StatusCode.findByValue(iprot.readI32());
        struct.setStatusCodeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

